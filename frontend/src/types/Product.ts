export interface Product {
  id: number;
  code: string;
  name: string;
  brand: string;
  type: string;
  description: string;
  createdAt: Date;
  updatedAt: Date;
  category: string;
}

export type NewProductRequest = Omit<
  Product,
  "created_at" | "updated_at" | "id"
>;

export type UpdateProductRequest = NewProductRequest;
