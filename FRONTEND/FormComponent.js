import React, { useState } from 'react';
import axios from 'axios';

const FormComponent = () => {
    const [formData, setFormData] = useState({
        serialId: '',
        name: '',
        numberOfParts: '',
        assertType: ''
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('/api/submitForm', formData)
            .then(response => {
                console.log('Form submitted successfully');
            })
            .catch(error => {
                console.error('Error submitting form:', error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="serialId" value={formData.serialId} onChange={handleChange} placeholder="Serial ID" />
            <input type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name" />
            <input type="number" name="numberOfParts" value={formData.numberOfParts} onChange={handleChange} placeholder="Number of Parts" />
            <select name="assertType" value={formData.assertType} onChange={handleChange}>
                <option value="type1">Type 1</option>
                <option value="type2">Type 2</option>
            </select>
            <button type="submit">Submit</button>
        </form>
    );
};

export default FormComponent;
