import {
    Box,
    Collapse,
    Flex,
    Icon,
    Text,
    useDisclosure,
} from "@chakra-ui/react";

import { FaClipboardCheck, FaRss } from "react-icons/fa";
import { AiFillGift } from "react-icons/ai";
import { BsGearFill } from "react-icons/bs";
import { HiCode, HiCollection } from "react-icons/hi";
import { MdHome, MdKeyboardArrowRight } from "react-icons/md";
import React from "react";

import NavItem from "./NavItem";

export default function Sidebar(props) {
    const integrations = useDisclosure();

    return (<Box
        as="nav"
        pos="fixed"
        top="0"
        left="0"
        zIndex="sticky"
        h="full"
        pb="10"
        overflowX="hidden"
        overflowY="auto"
        bg="white"
        _dark={{ bg: "gray.800" }}
        border
        color="inherit"
        borderRightWidth="1px"
        w="60"
        {...props}
    >
        <Flex px="4" py="5" align="center">
            <Text
                fontSize="2xl"
                ml="2"
                color="brand.500"
                _dark={{ color: "white" }}
                fontWeight="semibold"
            >
                A Firm
            </Text>
        </Flex>
        <Flex
            direction="column"
            as="nav"
            fontSize="sm"
            color="gray.600"
            aria-label="Main Navigation"
        >
            <NavItem icon={MdHome} link="/devices">Devices</NavItem>
            <NavItem icon={FaRss}>Articles</NavItem>
            <NavItem icon={HiCollection}>Collections</NavItem>
            <NavItem icon={FaClipboardCheck}>Checklists</NavItem>
            <NavItem icon={HiCode} onClick={integrations.onToggle}>
                Integrations
                <Icon
                    as={MdKeyboardArrowRight}
                    ml="auto"
                    transform={integrations.isOpen && "rotate(90deg)"}
                />
            </NavItem>
            <Collapse in={integrations.isOpen}>
                <NavItem pl="12" py="2">
                    Shopify
                </NavItem>
                <NavItem pl="12" py="2">
                    Slack
                </NavItem>
                <NavItem pl="12" py="2">
                    Zapier
                </NavItem>
            </Collapse>
            <NavItem icon={AiFillGift}>Changelog</NavItem>
            <NavItem icon={BsGearFill}>Settings</NavItem>
        </Flex>
    </Box>);
}