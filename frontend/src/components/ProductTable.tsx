import type { TableColumnsType, TablePaginationConfig } from "antd";
import { Table } from "antd";
import { useEffect, useState } from "react";
import { Product } from "../types/Product";
import { PageData } from "../types/common";

interface Props {
  pageData: PageData<Product>;
  onChangePage: (args: { page: number; size: number }) => Promise<void>;
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

export default function ProductTable({ pageData, onChangePage }: Props) {

  console.log("pageData:",pageData);

  const [pagination, setPagination] = useState<TablePaginationConfig>();
  const [loading, setLoading] = useState(false);

  const handleTableChange = async (pagination: TablePaginationConfig) => {
    console.log("paginaiton: ",pagination);
    try {
      setLoading(true);
      await onChangePage({
        page: (pagination.current || 2 ) - 1,
        size: pagination.pageSize || 10,
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const pagination: TablePaginationConfig = {
      current: pageData.page + 1,
      pageSize: pageData.limit,
      total: pageData.totalElements
    };
    setPagination(pagination);
  }, [pageData]);

  return (
    <Table
      columns={columns}
      dataSource={pageData.data}
      scroll={{ x: 1500, y: 300 }}
      pagination={pagination}
      onChange={handleTableChange}
      loading={loading}
    />
  );
}
