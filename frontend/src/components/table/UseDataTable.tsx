import React from 'react'
import DataTable, { Column } from './DataTable';

interface Item  {
    id:string;
    name:string;
    age:number;
  }

// Define a custom component for the edit button column
const EditButton = ({item}:{item:Item}) => {
    const handleEdit = () => {
      // Handle edit logic here
      console.log('Edit button clicked:', item);
    };
  
    return <button onClick={handleEdit}>Edit</button>;
  };

  // Sample data
  const data  : Item[] = [
    { id: "1", name: 'John Doe', age: 25 },
    { id: "2", name: 'Jane Smith', age: 30 },
    { id: "3", name: 'Bob Johnson', age: 40 },
  ];
  
  // Define the columns configuration, including the custom component for the edit button column
  const columns : Column<Item>[] = [
    { header: 'ID', accessor: 'id' },
    { header: 'Name', accessor: 'name' },
    { header: 'Age', accessor: 'age' },
    { header: 'Edit', accessor: 'name', Component: EditButton }, // Add the custom component for the edit button column
  ];
  
 export default function UseDataTable() {
    return (
      <div>
        <h1>Dynamic Table Example</h1>
        <DataTable datasource={data} columns={columns} />
      </div>
    );
  }