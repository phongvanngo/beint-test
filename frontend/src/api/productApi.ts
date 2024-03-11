import { Product } from "../types/Product";
import { PageData } from "../types/common";
import axiosClient from "./axiosClient";
import queryString from 'query-string';


export interface GetProductRequest {
    page: number;
    limit:number;
}

export async function getProducts(getProductRequest: GetProductRequest) : Promise<PageData<Product>> {
    const queryStringified = queryString.stringify(getProductRequest);
    return (await axiosClient.get(`/products?${queryStringified}`)).data;
}


