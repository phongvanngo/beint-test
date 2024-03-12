/* eslint-disable react-hooks/exhaustive-deps */
import { PlusOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { useEffect, useState } from "react";
import {
  createProduct,
  deleteProduct,
  getProducts,
  updateProduct,
} from "../api/productApi";
import AppModal from "../components/AppModal";
import ProductForm from "../components/ProductForm";
import ProductTable from "../components/ProductTable";
import {
  NewProductRequest,
  Product,
  UpdateProductRequest,
} from "../types/Product";
import { PageData } from "../types/common";

export default function ProductPage() {
  const [productPageData, setProductPageData] = useState<PageData<Product>>();
  const [idEditingProduct, setIdEditingProduct] = useState<number>();
  const [openCreateNewModal, setOpenCreateNewModal] = useState(false);
  const [editingProduct, setEditingProduct] =
    useState<UpdateProductRequest | null>();

  const handleCreateProduct = async (product: NewProductRequest) => {
    await createProduct(product);
    await handleFetchProducts();
  };

  const handleUpdateProduct = async (product: UpdateProductRequest) => {
    if (idEditingProduct) {
      await updateProduct(idEditingProduct, product);
      await handleFetchProducts();
    }
  };

  const handleDelete = async (id: number) => {
    await deleteProduct(id);
    await handleFetchProducts();
  };

  async function handleFetchProducts(
    {
      page,
      size,
    }: {
      page: number;
      size: number;
    } = { page: productPageData?.page || 1, size: productPageData?.limit || 10 }
  ) {
    const response = await getProducts({ page: page - 1, size });
    setProductPageData(response);
    updateQueryParams(page, size);
  }

  useEffect(() => {
    const searchParams = new URLSearchParams(window.location.search);
    const page = parseInt(searchParams.get("page") || "1");
    const size = parseInt(searchParams.get("size") || "10");
    handleFetchProducts({ page, size });
  }, []);

  const updateQueryParams = (page: number, size: number) => {
    const searchParams = new URLSearchParams(window.location.search);

    if (page) {
      searchParams.set("page", page.toString());
    }

    if (size) {
      searchParams.set("size", size.toString());
    }

    const newUrl = `${window.location.pathname}?${searchParams.toString()}`;
    window.history.replaceState({}, "", newUrl);
  };

  if (!productPageData) return null;

  return (
    <div>
      <AppModal
        title={editingProduct ? "Edit Product" : "Create Product"}
        onClose={() => {
          setOpenCreateNewModal(false);
        }}
        open={openCreateNewModal}
        Component={
          <ProductForm
            editingProduct={editingProduct ? editingProduct : undefined}
            onSubmit={(e) => {
              if (!editingProduct) handleCreateProduct(e);
              else handleUpdateProduct(e);
              setOpenCreateNewModal(false);
            }}
          />
        }
      />
      <div
        style={{
          width: "100%",
          justifyContent: "space-between",
          alignItems: "center",
          display: "flex",
        }}
      >
        <h1>Products</h1>
        <div>
          <Button
            type="primary"
            shape="round"
            icon={<PlusOutlined />}
            size={"large"}
            onClick={() => {
              setEditingProduct(null);
              setOpenCreateNewModal(true);
            }}
          >
            Create New Product
          </Button>
        </div>
      </div>
      <ProductTable
        pageData={productPageData}
        onChangePage={async ({ page, size }) => {
          handleFetchProducts({ page, size });
        }}
        onDelete={(e) => {
          handleDelete(e.id);
        }}
        onUpdate={(e) => {
          setEditingProduct(e);
          setIdEditingProduct(e.id);
          setOpenCreateNewModal(true);
        }}
      />
    </div>
  );
}
