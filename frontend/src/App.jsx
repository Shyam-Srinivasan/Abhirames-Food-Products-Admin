import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter, Route, Routes, Navigate, useLocation} from 'react-router-dom';


import {SignUpPage} from "./Components/Scripts/SignUpPage";
import {SignInPage} from "./Components/Scripts/SignInPage";
import {HomePage} from "./Components/Scripts/HomePage";
import Navbar from "./Components/Scripts/Navbar";

function Layout({children}) {
    const location = useLocation();
    const hideNavbar = ["/signIn", "/signUp"].includes(location.pathname);

    return(
        <>
            {!hideNavbar && <Navbar/>}
            <div>
                {children}
            </div>
        </>
    );
}

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Layout>
                    <Routes>
                        <Route path="/" element={<Navigate to="/home" replace/>}/>
                        <Route path="/signUp" element={<SignUpPage/>}/>
                        <Route path="/signIn" element={<SignInPage/>}/>
                        <Route path="/home" element={<HomePage/>}/>
                        {/*<Route path="/allProducts" element={<AllProducts/>}/>*/}
                        <Route path="*" element={<Navigate to="/signUp" replace/>}/>
                    </Routes>
                </Layout>
            </BrowserRouter>
        </div>
    );
}

export default App;
