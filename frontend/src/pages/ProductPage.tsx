import React, { useEffect, useState } from "react";
import { Product } from "../types/Product";
import { getProducts } from "../api/productApi";
import ProductTable from "../components/ProductTable";
import { PageData } from "../types/common";

type Props = {};

export default function ProductPage({}: Props) {
  const [productPageData, setProductPageData] = useState<PageData<Product>>();
  async function handleFetchProducts() {
    const response = await getProducts({ page: 0, size: 100 });
    setProductPageData(response);
  }
  useEffect(() => {
    handleFetchProducts();
  }, []);

  if (!productPageData) return null;

  return (
    <div>
      <ProductTable
        pageData={productPageData}
        onChangePage={async ({ page, size }) => {
          console.log("get: ",page,size);
          const response = await getProducts({ page, size });
          setProductPageData(response);
        }}
      />
    </div>
  );
}
