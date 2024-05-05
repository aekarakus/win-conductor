import { Alert, AlertDescription, AlertIcon, AlertTitle } from "@chakra-ui/react";

export default function ErrorPage(){
    return <Alert status="error"
        height="40px"
        width="500px"
        mx="auto"
        mt="10px"
        borderRadius="10px"
    >
        <AlertIcon />
        <AlertTitle>An error occured!</AlertTitle>
        <AlertDescription>Check your network connectivity!</AlertDescription>        
    </Alert>
}