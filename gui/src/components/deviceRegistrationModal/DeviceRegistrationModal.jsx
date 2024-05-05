import { Button, FormControl, FormErrorMessage, FormLabel, Input, Modal, ModalBody, ModalCloseButton, ModalContent, ModalFooter, ModalHeader, ModalOverlay } from "@chakra-ui/react";
import { useForm } from "react-hook-form";

export default function DeviceRegistrationModal({ isOpen, onClose, handleRegister }) {

    const {
        handleSubmit,
        register,
        formState: { errors, isSubmitting },
    } = useForm();

    function onSubmit(values) {
        console.log(values);
        fetch("http://localhost:8080/api/devices/register", {
            method: "POST",
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify(values),
        })
    }

    return (
        <Modal
            isOpen={isOpen}
            onClose={onClose}
            blockScrollOnMount={false}
        >
            <ModalOverlay />
            <ModalContent>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <ModalHeader>Register a device</ModalHeader>
                    <ModalCloseButton />
                    <ModalBody pb={6}>
                        <FormControl isInvalid={errors.name}>
                            <FormLabel htmlFor="name">Device name</FormLabel>
                            <Input
                                placeholder='Device name'
                                {
                                ...register('name', {
                                    required: "This field is required",
                                })
                                }
                            />
                            <FormErrorMessage>
                                {errors.name && errors.name.message}
                            </FormErrorMessage>
                        </FormControl>

                        <FormControl isInvalid={errors.name} mt={4}>
                            <FormLabel htmlFor="address">Address(v4)</FormLabel>
                            <Input
                                placeholder='Address'
                                {
                                ...register('address', {
                                    required: "This field is required",
                                })
                                }
                            />
                        </FormControl>
                    </ModalBody>

                    <ModalFooter>
                        <Button type="submit" isLoading={isSubmitting} colorScheme='blue' mr={3}>
                            Register
                        </Button>
                        <Button type="button" onClick={onClose}>Cancel</Button>
                    </ModalFooter>
                </form>
            </ModalContent>
        </Modal>
    );
}