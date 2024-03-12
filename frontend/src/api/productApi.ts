import {
  NewProductRequest,
  Product,
  UpdateProductRequest,
} from "../types/Product";
import { DataResponse, PageData } from "../types/common";
import axiosClient from "./axiosClient";
import queryString from "query-string";

export interface GetProductRequest {
  page: number;
  size: number;
}

export async function getProducts(
  getProductRequest: GetProductRequest
): Promise<PageData<Product>> {
  const queryStringified = queryString.stringify(getProductRequest);
  return (await axiosClient.get(`/products?${queryStringified}`)).data;
}

export async function createProduct(
  newProductRequest: NewProductRequest
): Promise<DataResponse<Product>> {
  return (await axiosClient.post(`/products`, newProductRequest)).data;
}

export async function updateProduct(
  id: number,
  updateProductRequest: UpdateProductRequest
): Promise<DataResponse<Product>> {
  return (await axiosClient.put(`/products/${id}`, updateProductRequest)).data;
}

export async function deleteProduct(id: number): Promise<string> {
  return (await axiosClient.delete(`/products/${id}`)).data;
}
