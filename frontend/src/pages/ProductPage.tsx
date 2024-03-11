import React, { useEffect, useState } from "react";
import { Product } from "../types/Product";
import { getProducts } from "../api/productApi";
import ProductTable from "../components/ProductTable";
import { PageData } from "../types/common";

type Props = {};

export default function ProductPage({}: Props) {
  const [productPageData, setProductPageData] = useState<PageData<Product>>();
  async function handleFetchProducts({
    page,
    size,
  }: {
    page: number;
    size: number;
  }) {
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
      <ProductTable
        pageData={productPageData}
        onChangePage={async ({ page, size }) => {
          handleFetchProducts({ page, size });
        }}
      />
    </div>
  );
}
