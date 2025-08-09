import {
  Flex,
  Icon,
  useColorModeValue,
  useDisclosure,
} from "@chakra-ui/react";

import React from "react";
import { Link } from "react-router-dom";


export default function NavItem(props){
    const { icon, children, link, ...rest } = props;
    return (
      <Flex
        align="center"
        px="4"
        pl="4"
        py="3"
        cursor="pointer"
        color="inherit"
        _dark={{ color: "gray.400" }}
        _hover={{
          bg: "rgb(2 106 162)",
          _dark: { bg: "gray.900" },
          color: "white",
        }}
        role="group"
        fontWeight="thin"
        transition=".15s ease"
        {...rest}
      >
        {icon && (
          <Icon
            mx="2"
            boxSize="4"
            _groupHover={{
              color: "white",
            }}
            as={icon}
          />
        )}
        <Link to={link}>{children}</Link>
      </Flex>
    );
  };