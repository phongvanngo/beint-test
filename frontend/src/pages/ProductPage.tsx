import React, { useEffect, useState } from 'react'
import { Product } from '../types/Product';
import { getProducts } from '../api/productApi';
import ProductTable from '../components/ProductTable';

type Props = {}

export default function ProductPage({}: Props) {
    const [products, setProducts] = useState<Product[]>([]);
    async function handleFetchProducts() {
        const response = await getProducts({page: 0, limit: 10});
        setProducts(response.data);
    }
    useEffect(() => {
        handleFetchProducts();
    }, []);
    
  return (
    <div>
        <ProductTable data={products}/>
    </div>
  )
}