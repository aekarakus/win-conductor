import { Box, Flex, TableContainer, Table, TableCaption, Thead, Tr, Th, Td, Tbody, Tfoot, GridItem } from "@chakra-ui/react";
import { getCoreRowModel, useReactTable } from "@tanstack/react-table";
import {
    createColumnHelper,
    flexRender,
} from '@tanstack/react-table';
import { useState, useReducer } from "react";
import { useLoaderData } from "react-router-dom";
import TableToolbar from "../components/tableToolbar/TableToolbar";


const columnHelper = createColumnHelper();

const columns = [
    columnHelper.accessor(row => row.id, {
        id: 'id',
        cell: info => <i>{info.getValue()}</i>,
        header: () => <span>ID</span>,
        footer: info => info.column.id,
    }),
    columnHelper.accessor('address', {
        cell: info => info.getValue(),
        footer: info => info.column.id,
    }),
    columnHelper.accessor('name', {
        header: () => 'Name',
        cell: info => info.getValue(),
        footer: info => info.column.id,
    }),
    columnHelper.accessor('Profile Info', {
        header: () => <span>Profile Info</span>,
        cell: info => (info.cell.row.original.profileInfo === null ? '' : info.cell.row.original.profileInfo.name),
        footer: info => info.column.id,
    }),
]
export default function Devices() {
    const data = useLoaderData();

    const table = useReactTable({
        data, columns, getCoreRowModel: getCoreRowModel(),
    });
    return (<>
        <GridItem w="full" h="full" my="3" colSpan="12">
            <Flex mt="10px" flexDirection="column" mx="auto" bg="white" w="full" border="1px solid #d3d3d3" borderRadius="10px">
                
                <TableToolbar title="Devices"/>
                <TableContainer>
                    <Table variant='simple' size="sm">
                        <TableCaption>Registered devices...</TableCaption>
                        <Thead>
                            {table.getHeaderGroups().map(headerGroup => (
                                <Tr key={headerGroup.id}>
                                    {headerGroup.headers.map(header => (
                                        <Th key={header.id}>
                                            {header.isPlaceholder
                                                ? null
                                                : flexRender(
                                                    header.column.columnDef.header,
                                                    header.getContext()
                                                )}
                                        </Th>
                                    ))}
                                </Tr>
                            ))}
                        </Thead>
                        <Tbody>
                            {table.getRowModel().rows.map(row => (
                                <Tr key={row.id}>
                                    {row.getVisibleCells().map(cell => (
                                        <Td key={cell.id}>
                                            {flexRender(cell.column.columnDef.cell, cell.getContext())}
                                        </Td>
                                    ))}
                                </Tr>
                            ))}
                        </Tbody>
                    </Table>
                </TableContainer></Flex>
        </GridItem>
    </>);
}



export async function loader() {

    const response = await fetch("http://localhost:8080/api/devices/list");
    if (!response.ok) {
        throw { message: "Could not fetch devices." }
    } else {
        return response;
    }

}