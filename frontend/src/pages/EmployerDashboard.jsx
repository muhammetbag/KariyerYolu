import { useState, useEffect } from 'react';
import api from '../api/axiosConfig';

export default function EmployerDashboard() {
    const [jobs, setJobs] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');

    useEffect(() => {
        // Normalde backend'den giriş yapan işverenin ID'si JWT'den alınır.
        // Şimdilik test amaçlı 3 numaralı ID (Kaydettiğimiz CEO) çekiliyor.
        api.get('/jobs/employer/3').then(res => setJobs(res.data)).catch(console.error);
    }, []);

    const handleCreateJob = async (e) => {
        e.preventDefault();
        try {
            await api.post('/jobs', { title, description, location, employerId: 3 });
            alert('İlan başarıyla yayınlandı!');
            window.location.reload();
        } catch (error) {
            alert('İlan yayınlanırken bir hata oluştu.');
        }
    };

    return (
        <div className="max-w-6xl mx-auto mt-10 grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="bg-white p-8 rounded-lg shadow-md">
                <h2 className="text-2xl font-bold mb-6 text-gray-800">Yeni İlan Ver</h2>
                <form onSubmit={handleCreateJob} className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Pozisyon (Başlık)</label>
                        <input type="text" required className="mt-1 w-full p-2 border border-gray-300 rounded"
                            value={title} onChange={(e) => setTitle(e.target.value)} />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Şehir / Konum</label>
                        <input type="text" required className="mt-1 w-full p-2 border border-gray-300 rounded"
                            value={location} onChange={(e) => setLocation(e.target.value)} />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Açıklama</label>
                        <textarea required className="mt-1 w-full p-2 border border-gray-300 rounded h-32"
                            value={description} onChange={(e) => setDescription(e.target.value)}></textarea>
                    </div>
                    <button type="submit" className="w-full bg-blue-600 text-white p-3 rounded font-bold hover:bg-blue-700 transition">
                        İlanı Yayınla
                    </button>
                </form>
            </div>

            <div className="bg-white p-8 rounded-lg shadow-md overflow-y-auto max-h-[600px]">
                <h2 className="text-2xl font-bold mb-6 text-gray-800">Yayındaki İlanlarım</h2>
                <div className="space-y-4">
                    {jobs.map(job => (
                        <div key={job.id} className="p-4 border border-gray-200 rounded-lg hover:bg-gray-50">
                            <h3 className="font-bold text-lg text-gray-800">{job.title}</h3>
                            <p className="text-sm text-gray-500 mb-2">📍 {job.location}</p>
                            <p className="text-gray-600 text-sm line-clamp-2">{job.description}</p>
                        </div>
                    ))}
                    {jobs.length === 0 && <p className="text-gray-500 italic">Henüz ilanınız bulunmuyor.</p>}
                </div>
            </div>
        </div>
    );
}
