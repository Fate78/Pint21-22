
import { Outlet } from "react-router-dom";
import Navbar from '../menu/NavBar';
import NavBarTop from '../menu/NavBarTop';

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