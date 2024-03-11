import React from "react";
import { Table } from "antd";
import type { TableColumnsType } from "antd";
import { Product } from "../types/Product";

interface Props {
  data: Product[];
}

interface DataType {
  key: React.Key;
  name: string;
  age: number;
  address: string;
}

const columns: TableColumnsType<Product> = [
  {
    title: "ID",
    width: 100,
    dataIndex: "id",
    key: "id",
    fixed: "left",
  },
  {
    title: "code",
    width: 100,
    dataIndex: "code",
    key: "code",
  },
  {
    title: "brand",
    dataIndex: "brand",
    key: "brand",
    width: 150,
  },
  {
    title: "type",
    dataIndex: "type",
    key: "type",
    width: 150,
  },
  {
    title: "description:",
    dataIndex: "description",
    key: "description",
    width: 150,
  },
  {
    title: "Action",
    key: "operation",
    fixed: "right",
    width: 100,
    render: () => <a>action</a>,
  },
];

export default function ProductTable({ data }: Props) {
  return (
    <Table columns={columns} dataSource={data} scroll={{ x: 1500, y: 300 }} />
  );
}
