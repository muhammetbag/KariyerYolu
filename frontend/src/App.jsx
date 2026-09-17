import { useState } from 'react';
import axios from 'axios';
import './index.css';

function App() {
  const [keyword, setKeyword] = useState('');
  const [jobs, setJobs] = useState([]);
  const [hasSearched, setHasSearched] = useState(false);

  const searchJobs = async (e) => {
    e.preventDefault();
    if (!keyword) return;
    
    try {
      const response = await axios.get('http://localhost:8080/api/jobs/search?keyword=' + keyword);
      setJobs(response.data);
      setHasSearched(true);
    } catch (error) {
      console.error('Arama hatası:', error);
    }
  };

  return (
    <div className="container">
      <h1 className="title">Kariyer.net Clone</h1>
      
      <form onSubmit={searchJobs} className="search-form">
        <input 
          type="text" 
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          placeholder="Pozisyon, yetenek veya kelime ara... (Örn: Java)" 
          className="search-input"
        />
        <button type="submit" className="search-button">
          İş Bul
        </button>
      </form>

      <div>
        {hasSearched && jobs.length === 0 && (
          <p className="no-results">Aradığınız kritere uygun ilan bulunamadı.</p>
        )}
        
        {jobs.map((job, index) => (
          <div key={index} className="job-card">
            <h2 className="job-title">{job.title}</h2>
            <p className="job-location">📍 {job.location}</p>
            <p className="job-desc">{job.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
