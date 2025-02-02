import * as React from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { useTranslation } from "react-i18next";
import { Link as RouterLink } from "react-router-dom";
import { useCurrencyFormatter } from "@hooks";
import { Order } from "@models";

export interface OrderListProps {
  orders: Order[];
}

export const OrderTable: React.FC<OrderListProps> = ({ orders }) => {
  const { t } = useTranslation(["orders", "common"]);
  const formatCurrency = useCurrencyFormatter();

  const columns: GridColDef<Order>[] = [
    {
      field: "id",
      headerName: t("order.id") as string,
      renderCell: (params) => <RouterLink to={`/orders/${params.row.id}`}>{params.row.id}</RouterLink>,
      width: 100,
    },
    {
      field: "order_type",
      headerName: t("order.type") as string,
      width: 100,
    },
    {
      field: "payment_method",
      headerName: t("order.paymentMethod") as string,
      width: 150,
    },
    {
      field: "total_no_tax",
      headerName: t("order.totalNoTax") as string,
      align: "right",
      valueFormatter: ({ value }) => formatCurrency(value),
      width: 150,
    },
    {
      field: "total_tax",
      headerName: t("order.totalTax") as string,
      align: "right",
      valueFormatter: ({ value }) => formatCurrency(value),
      width: 100,
    },
    {
      field: "total_price",
      headerName: t("order.totalPrice") as string,
      align: "right",
      valueFormatter: ({ value }) => formatCurrency(value),
      width: 100,
    },
    {
      field: "bookedAt",
      headerName: t("order.bookedAt") as string,
      type: "string",
      valueGetter: ({ value }) => value && new Date(value).toLocaleString("de-DE"),
      flex: 1,
    },
  ];

  return (
    <DataGrid
      autoHeight
      rows={orders ?? []}
      columns={columns}
      disableRowSelectionOnClick
      sx={{ mt: 2, p: 1, boxShadow: (theme) => theme.shadows[1] }}
    />
  );
};
