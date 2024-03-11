import React from 'react';

export type Column<T> = {
  header: string;
  accessor: string;
  Component?: React.ComponentType<{ item: T }>;
};

type Props<T> = {
  datasource: T[];
  columns: Column<T>[];
};

function DataTable<T>({ datasource, columns }: Props<T>) {
  return (
    <table className="border-collapse">
      <thead>
        <tr>
          {columns.map((column) => (
            <th
              key={String(column.accessor)}
              className="px-4 py-2 font-semibold text-gray-700 bg-gray-200"
            >
              {column.header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {datasource.map((item: any, index) => (
          <tr key={index} className={index % 2 === 0 ? 'bg-gray-100' : 'bg-white'}>
            {columns.map((column) => (
              <td
                key={String(column.accessor)}
                className="px-4 py-2 border-b border-gray-200"
              >
                {column.Component ? (
                  <column.Component item={item} />
                ) : (
                  <div>
                    {item[column.accessor] ?? 'N/A'}
                  </div>
                )}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default DataTable;