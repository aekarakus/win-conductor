import { Outlet } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import { Flex, Grid } from "@chakra-ui/react";
import Header from "../components/header/Header";

export default function RootLayout() {
    return (
        <>
            <Flex direction="row" w="full" h="full">
                <Sidebar />
                <Grid as="section" id="main-section" w="full" h="full" m="5" templateRows="repeat(24, 1fr)" templateColumns="repeat(12, 1fr)">
                    <Header />
                    <Outlet />
                </Grid>
            </Flex>
        </>
    );
}