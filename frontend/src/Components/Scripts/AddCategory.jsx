import { Modal, Button, Form, Alert } from "react-bootstrap";
import { useState } from "react";
import axios from "axios";

const API_BASE = `http://${window.location.hostname}:8000`;

const AddCategory = ({ show, handleClose }) => {
    const [form, setForm] = useState({
        category_name: "",
    });

    const [submitting, setSubmitting] = useState(false);
    const [error, setError] = useState("");

    const onChange = (e) => {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    };

    const submitCategory = async (e) => {
        e.preventDefault();
        setSubmitting(true);
        setError("");

        try {
            const response = await axios.post(
                `${API_BASE}/categoryList/createCategory`,
                form,
                { validateStatus: () => true }
            );

            if (response.status === 200 || response.status === 201) {
                setForm({ category_name: ""});
                handleClose();
            } else {
                setError(response.data?.message || "Failed to create category");
            }
        } catch (err) {
            setError("Server error. Please try again.");
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Form onSubmit={submitCategory}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Category</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    {error && <Alert variant="danger">{error}</Alert>}

                    <Form.Group className="mb-3">
                        <Form.Label>Category Name</Form.Label>
                        <Form.Control
                            type="text"
                            name="category_name"
                            value={form.category_name}
                            onChange={onChange}
                            placeholder="Enter category name"
                            required
                        />
                    </Form.Group>

                </Modal.Body>

                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose} disabled={submitting}>
                        Cancel
                    </Button>
                    <Button variant="primary" type="submit" disabled={submitting}>
                        {submitting ? "Creating..." : "Create"}
                    </Button>
                </Modal.Footer>
            </Form>
        </Modal>
    );
};

export default AddCategory;
