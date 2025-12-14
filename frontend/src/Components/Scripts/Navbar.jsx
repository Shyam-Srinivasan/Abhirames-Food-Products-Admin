import React, { useState } from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../Styles/Navbar.css";
import AddCategory from "./AddCategory";

function NavbarComponent() {
    const [showAddCategory, setShowAddCategory] = useState(false);

    return (
        <>
            <Navbar variant="dark" expand="lg" fixed="top" className="custom-navbar">
                <Container fluid>
                    <Navbar.Brand as={Link} to="/home">
                        <span className="company-name">Abhirames Food Products</span>
                    </Navbar.Brand>

                    <Navbar.Toggle aria-controls="basic-navbar-nav" className="ms-auto" />

                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="nav-titles ms-auto">
                            <Nav.Link as={Link} to="/allProducts">All Products</Nav.Link>
                            <Nav.Link onClick={() => setShowAddCategory(true)}>
                                Add Category
                            </Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            
            <AddCategory
                show={showAddCategory}
                handleClose={() => setShowAddCategory(false)}
            />
        </>
    );
}

export default NavbarComponent;
