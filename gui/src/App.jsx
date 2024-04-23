import Devices from './layouts/Devices';
import HomePage from './layouts/HomePage';
import SidebarLayout from './layouts/SidebarLayout';
import './App.css'
import { Route, RouterProvider, createBrowserRouter } from 'react-router-dom'

const router = createBrowserRouter([
  {
    path: '/', element: <SidebarLayout />,
    children: [

      { path: '/', element: <HomePage /> },
      { path: '/devices', element: <Devices /> }
    ]
  },
]);

function App() {

  return (
    <RouterProvider router={router} />
  )
}

export default App
