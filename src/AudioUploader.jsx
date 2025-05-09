import React from 'react'
import axios from 'axios';
import './App.css'

const AudioUploader = () => {
  const [file, setFile] = React.useState(null);
  const [transcription, setTranscription] = React.useState('');
  const [isLoading, setIsLoading] = React.useState(false);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!file) return;
  
    setIsLoading(true);
    
    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8080/api/transcribe', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      setTranscription(response.data);
    } catch (error) {
      console.error('Error uploading file:', error);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <div className="audio-transcriber-container">
      <h1>Audio to Text Transcriber</h1>
      <div className="file-inp">
        <input type="file" accept="audio/*" onChange={handleFileChange} disabled={isLoading} />
      </div>
      <button 
        className='upload-btn' 
        onClick={handleUpload} 
        disabled={isLoading || !file}
      >
        {isLoading ? 'Transcribing...' : 'Upload and transcribe'}
      </button>
      
      {isLoading && (
        <div className="loading-container">
          <div className="loading-spinner"></div>
          <p>Processing your audio...</p>
        </div>
      )}
      
      {!isLoading && (
        <div className="transcribe-result">
          <h2>Transcription Result</h2>
          <p>{transcription}</p>
        </div>
      )}

      <div className="copywrite-container">
        <span className='copywrite'>Developed by Vishesh Purkait</span>
      </div>
    </div>
  )
}

export default AudioUploader;



/* old code */
// const AudioUploader = () => {
//   const [file, setFile] = React.useState(null);
//   const [transcription, setTranscription] = React.useState('');

//   const handleFileChange = (e) => {
//     setFile(e.target.files[0]);
//   };

//   const handleUpload = async () => {
//     if (!file) return;
//     const formData = new FormData();
//     formData.append('file', file);

//     try {
//       const response = await axios.post('http://localhost:8080/api/transcribe', formData, {
//         headers: {
//           'Content-Type': 'multipart/form-data'
//         }
//       });
//       setTranscription(response.data);
//     } catch (error) {
//       console.error('Error uploading file:', error);
//     }
//   }

//   return (
//     <div className="audio-transcriber-container">
//       <h1>Audio to Text Transcriber</h1>
//       <div className="file-inp">
//         <input type="file" accept="audio/*" onChange={handleFileChange} />
//       </div>
//       <button className='upload-btn' onClick={handleUpload}>Upload and transcribe</button>
//       <div className="transcribe-result">
//         <h2>Transcription Result</h2>
//         <p>{transcription}</p>
//       </div>
//     </div>
//   )
// }