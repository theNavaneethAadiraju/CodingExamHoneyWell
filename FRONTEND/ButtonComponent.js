import React from 'react';
import axios from 'axios';

const ButtonComponent = () => {
    const handleGenerateFile = () => {
        axios.get('/api/downloadFile')
            .then(response => {
                // Handle file download
            })
            .catch(error => {
                console.error('Error downloading file:', error);
            });
    };

    const handleSignFile = () => {
        // Code to send file to backend for signing
    };

    return (
        <div>
            <button onClick={handleGenerateFile}>Generate File</button>
            <button onClick={handleSignFile}>Digital Sign</button>
        </div>
    );
};

export default ButtonComponent;
