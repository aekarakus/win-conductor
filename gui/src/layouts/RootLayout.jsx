import { Outlet } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import { Flex } from "@chakra-ui/react";

export default function RootLayout() {
    return (
        <>
            <Flex direction="row" w="full" h="full">
                <Sidebar />
                <Outlet />
            </Flex>
        </>
    );
}