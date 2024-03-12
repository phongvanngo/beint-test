import type { TableColumnsType, TablePaginationConfig } from "antd";
import { Button, Flex, Popconfirm, Table } from "antd";
import { useEffect, useState } from "react";
import { Product } from "../types/Product";
import { PageData } from "../types/common";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";

const pageSizeOptions = ["10", "20", "25"];

interface Props {
  pageData: PageData<Product>;
  onChangePage: (args: { page: number; size: number }) => Promise<void>;
  onUpdate: (product: Product) => void;
  onDelete: (product: Product) => void;
}

export default function ProductTable({
  pageData,
  onChangePage,
  onDelete,
  onUpdate,
}: Props) {
  const [pagination, setPagination] = useState<TablePaginationConfig>();
  const [loading, setLoading] = useState(false);

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
      title: "name",
      width: 150,
      dataIndex: "name",
      key: "name",
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
      title: "category",
      dataIndex: "category",
      key: "category",
      width: 150,
    },
    {
      title: "description",
      dataIndex: "description",
      key: "description",
      width: 150,
    },
    {
      title: "Action",
      key: "operation",
      fixed: "right",
      width: 100,
      render: (_, record) => (
        <Flex gap="small">
          <Button
            type="primary"
            icon={<EditOutlined />}
            onClick={() => onUpdate(record)}
          />

          <Popconfirm
            title="Delete the product"
            description="Are you sure to delete this product ?"
            onConfirm={() => {
              onDelete(record);
            }}
            okText="Yes"
            cancelText="No"
          >
            <Button type="primary" danger icon={<DeleteOutlined />} />
          </Popconfirm>
        </Flex>
      ),
    },
  ];

  const handleTableChange = async (pagination: TablePaginationConfig) => {
    try {
      setLoading(true);
      let page = pagination.current || 2;
      if (pagination.pageSize != pageData.limit) page = 1;
      await onChangePage({
        page,
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
      total: pageData.totalElements,
      pageSizeOptions: pageSizeOptions
    };
    setPagination(pagination);
  }, [pageData]);

  return (
    <div>
      <Table
        columns={columns}
        dataSource={pageData.data}
        scroll={{ x: 0, y: 500 }}
        pagination={pagination}
        onChange={handleTableChange}
        loading={loading}
      />
    </div>
  );
}
