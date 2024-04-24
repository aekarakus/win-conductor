import Devices, { loader as devicesLoader } from './layouts/Devices';
import HomePage from './layouts/HomePage';
import RootLayout from './layouts/RootLayout';
import './App.css'
import { Route, RouterProvider, createBrowserRouter } from 'react-router-dom'
import Applications from './layouts/Applications';
import ErrorPage from './layouts/ErrorPage';

const router = createBrowserRouter([
  {
    path: '/', element: <RootLayout />,
    children: [

      { index: true, element: <HomePage /> },
      {
        path: 'devices', element: <Devices />, loader: devicesLoader,
        errorElement: <ErrorPage />,
      },
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
