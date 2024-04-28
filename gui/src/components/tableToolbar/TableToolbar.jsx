import { ArrowForwardIcon, EmailIcon, PlusSquareIcon, SearchIcon } from "@chakra-ui/icons";
import { Box, Button, Flex, Icon, Input, InputGroup, InputLeftElement, Spacer, Stack, Text } from "@chakra-ui/react";
import { CiCirclePlus } from "react-icons/ci";
import { MdOutlineDevices } from "react-icons/md";
import { TiDeviceDesktop } from "react-icons/ti";

export default function TableToolbar({ title }) {
    return (
        <>
            <Flex id="table-toolbar__title" w="full" h="30px" my="4" mx="4" direction="row" alignItems="center">
                <Box w="40px" h="40px" borderRadius="100%" bg="rgb(224 242 254)" textAlign="center" alignContent="center">
                    <Icon color="#095986" as={TiDeviceDesktop} boxSize="4" mt={1}/>
                </Box>
                <Text fontWeight="hairline" fontSize="lg">{title}</Text>
                <Spacer />
                <Box>
                    <InputGroup size="sm">
                        <InputLeftElement pointerEvents="none">
                            <SearchIcon color='gray.500' />
                        </InputLeftElement>
                        <Input focusBorderColor="#095986" errorBorderColor="red" placeholder="Search..." w="96" size="sm" variant="filled" />
                    </InputGroup>
                </Box>
                <Button color="white" background="#095986" size="sm" ml="6" leftIcon={<CiCirclePlus />}  variant='solid'>
                    Register
                </Button>
            </Flex>
        </>
    )
}