import { SearchIcon } from "@chakra-ui/icons";
import { Box, Button, Circle, Flex, HStack, Icon, Input, InputGroup, InputLeftElement, Spacer, Text, useDisclosure } from "@chakra-ui/react";
import { CiCirclePlus } from "react-icons/ci";
import { TiDeviceDesktop } from "react-icons/ti";

export default function TableToolbar({ title, onModalOpen }) {
    return (
        <Flex id="table-toolbar" w="full" h="30px" my="4" px="4" direction="row" alignItems="center">
            <HStack>
                <Circle size="10" color='white' background="#095986">
                    <Icon as={TiDeviceDesktop} boxSize="4" />
                </Circle>
                <Text fontWeight="hairline" fontSize="lg">{title}</Text>
            </HStack>
            <Spacer />
            <HStack>
                <InputGroup size="sm">
                    <InputLeftElement pointerEvents="none">
                        <SearchIcon color='gray.500' />
                    </InputLeftElement>
                    <Input borderRadius="6" focusBorderColor="#095986" errorBorderColor="red" placeholder="Search..." w="96" size="sm" variant="filled" />
                </InputGroup>
                <Button onClick={onModalOpen} color="white" background="#095986" size="sm" leftIcon={<CiCirclePlus />} variant='solid'>
                    Register
                </Button>
            </HStack>
        </Flex>
    )
}