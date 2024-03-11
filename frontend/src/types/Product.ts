import { Category } from "./Category";

export interface Product {
    id: string;
    code:string;
    name:string;
    brand:string;
    type:string;
    description:string;
    createdAt:Date;
    updatedAt:Date;
    categories:Array<Category>;
}