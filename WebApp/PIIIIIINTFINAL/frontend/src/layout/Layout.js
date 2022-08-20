import { Outlet } from "react-router-dom";
import Navbar from '../menu/Navs/NavBar';
import NavBarTop from '../menu/Navs/NavBarTop';

const Layout = () => {
    return (
        <div>
            
            <Outlet />
        </div>
    )
}

export default Layout