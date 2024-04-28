import { Button, FormControl, FormLabel, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay } from "@chakra-ui/react";

export default function DeviceRegistrationModal({isOpen, onClose}){
    return (
        <Modal
        isOpen={isOpen}
        onClose={onClose}
        blockScrollOnMount={false}
    >
        <ModalOverlay />
        <ModalContent>
            <ModalHeader>Create your account</ModalHeader>
            <ModalCloseButton />
            <ModalBody pb={6}>
                <FormControl>
                    <FormLabel>First name</FormLabel>
                    <Input placeholder='First name' />
                </FormControl>

                <FormControl mt={4}>
                    <FormLabel>Last name</FormLabel>
                    <Input placeholder='Last name' />
                </FormControl>
            </ModalBody>

            <ModalFooter>
                <Button colorScheme='blue' mr={3}>
                    Save
                </Button>
                <Button onClick={onClose}>Cancel</Button>
            </ModalFooter>
        </ModalContent>
    </Modal>
    );
}