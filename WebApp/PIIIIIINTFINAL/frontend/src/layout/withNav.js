
import { Outlet } from "react-router-dom";
import Navbar from '../menu/Navs/NavBar';
import NavBarTop from '../menu/Navs/NavBarTop';

const withNav = () => {
    return (
        <div>
            <NavBarTop />
        <Navbar />
            <Outlet />
        </div>
    )
}

export default withNav