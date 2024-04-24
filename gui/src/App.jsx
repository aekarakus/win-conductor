import Devices from './layouts/Devices';
import HomePage from './layouts/HomePage';
import RootLayout from './layouts/RootLayout';
import './App.css'
import { Route, RouterProvider, createBrowserRouter } from 'react-router-dom'
import Applications from './layouts/Applications';

const router = createBrowserRouter([
  {
    path: '/', element: <RootLayout />,
    children: [

      { index: true, element: <HomePage /> },
      { path: 'devices', element: <Devices />, loader: async () => {
          const response = await fetch("http://localhost:8080/api/devices/list");
          if(!response.ok){

          } else {
            const resData = await response.json();
            return resData;
          }
      } },
      { path: 'applications', element: <Applications /> },
    ]
  },
]);

function App() {

  return (
    <RouterProvider router={router} />
  )
}

export default App
