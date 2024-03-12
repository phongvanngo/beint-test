/* eslint-disable react-hooks/exhaustive-deps */
import { Button, Form, Input } from "antd";
import { useEffect } from "react";
import { NewProductRequest, UpdateProductRequest } from "../types/Product";

const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 6 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 14 },
  },
};

interface Props {
  editingProduct?: UpdateProductRequest;
  onSubmit: (value: NewProductRequest | UpdateProductRequest) => void;
}

export default function ProductForm({ editingProduct, onSubmit }: Props) {
  console.log(editingProduct);

  const [form] = Form.useForm();

  useEffect(() => {
    if (editingProduct) form.setFieldsValue(editingProduct);
    else form.resetFields();
  }, [editingProduct, open]);

  return (
    <Form
      {...formItemLayout}
      form={form}
      variant="filled"
      style={{ maxWidth: 600 }}
      onFinish={(e) => {
        onSubmit(e);
        form.resetFields();
      }}
    >
      <Form.Item
        label="Code"
        name="code"
        rules={[{ required: true, message: "Please input!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="Name"
        name="name"
        rules={[{ required: true, message: "Please input!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        label="Category"
        name="category"
        rules={[{ required: true, message: "Please input!" }]}
      >
        <Input />
      </Form.Item>

      <Form.Item label="Brand" name="brand">
        <Input />
      </Form.Item>

      <Form.Item label="Type" name="type">
        <Input />
      </Form.Item>

      <Form.Item label="Description" name="description">
        <Input.TextArea rows={7} />
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 6, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
}
