import { Modal } from "antd";
import React, { ReactNode } from "react";

type Props = {
  title: string;
  onClose: () => void;
  open: boolean;
  Component: ReactNode;
};

const AppModal = ({ title, onClose, open, Component }: Props) => {
  return (
    <Modal
      footer={false}
      title={title}
      open={open}
      onCancel={onClose}
      maskClosable={false}
    >
      {open ? Component : null}
    </Modal>
  );
};
export default AppModal;
