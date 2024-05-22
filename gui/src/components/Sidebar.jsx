import {
    Box,
    Flex,
    Text,
    useDisclosure,
} from "@chakra-ui/react";

import { FaRss } from "react-icons/fa";
import { MdHome } from "react-icons/md";
import React from "react";
import { Link } from "react-router-dom";

import NavItem from "./NavItem";

export default function Sidebar(props) {

    return (<Box
        as="nav"
        zIndex="sticky"
        h="full"
        pb="10"
        overflowX="hidden"
        overflowY="auto"
        bg="#095986"
        _dark={{ bg: "gray.800" }}
        border
        color="white"
        borderRightWidth="1px"
        w="60"
        {...props}
    >
        <Link to="/">
            <Flex px="4" py="5" align="center">
                <Text
                    fontSize="2xl"
                    ml="2"
                    color="white"
                    _dark={{ color: "white" }}
                    fontWeight="semibold"
                >
                    A Firm
                </Text>
            </Flex></Link>
        <Flex
            direction="column"
            as="nav"
            fontSize="sm"
            color="white"
            aria-label="Main Navigation"
        >
            <NavItem icon={MdHome} link="/devices">Devices</NavItem>
            <NavItem icon={FaRss} link="/applications">Applications</NavItem>
        </Flex>
    </Box>);
}