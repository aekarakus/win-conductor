import { GridItem, Heading, Text } from "@chakra-ui/react";

export default function Header(){

    return (
        <>
            <GridItem id="header" colSpan="12">
                <Text mb="4">Devices</Text>
                <Heading>Devices List</Heading>
            </GridItem>
        </>
    )
}