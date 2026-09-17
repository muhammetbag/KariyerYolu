import { useState, useContext } from 'react';
import api from '../api/axiosConfig';
import { Search, MapPin, Building } from 'lucide-react';
import { AuthContext } from '../context/AuthContext';

export default function Home() {
    const [keyword, setKeyword] = useState('');
    const [jobs, setJobs] = useState([]);
    const [hasSearched, setHasSearched] = useState(false);
    const { user } = useContext(AuthContext);

    const handleSearch = async (e) => {
        e.preventDefault();
        try {
            const res = await api.get(`/jobs/search?keyword=${keyword}`);
            setJobs(res.data);
            setHasSearched(true);
        } catch (error) {
            console.error('Arama hatası', error);
        }
    };

    const handleApply = async (jobId) => {
        if (!user) {
            alert('Başvuru yapmak için giriş yapmalısınız!');
            return;
        }
        try {
            // Şimdilik test ID'si gönderiyoruz. JWT'den de backend alabilir.
            await api.post('/applications', { userId: 3, jobId: jobId });
            alert('Başvuru başarıyla tamamlandı!');
        } catch (error) {
            alert('Başvuru sırasında hata oluştu. (Daha önce başvurmuş olabilirsiniz)');
        }
    };

    return (
        <div className="max-w-4xl mx-auto mt-10">
            <div className="text-center mb-10">
                <h1 className="text-4xl font-extrabold text-gray-900 mb-4">Hayalindeki İşi Bul</h1>
                <p className="text-gray-500 text-lg">Binlerce güncel iş ilanı Kariyer.net Klon'da seni bekliyor.</p>
            </div>

            <form onSubmit={handleSearch} className="flex gap-2 bg-white p-2 rounded-full shadow-md mb-10">
                <div className="flex-1 flex items-center pl-4">
                    <Search className="text-gray-400 mr-2" />
                    <input type="text" placeholder="Pozisyon veya teknoloji ara (Örn: Java)..." 
                        className="w-full py-3 focus:outline-none text-lg"
                        value={keyword} onChange={(e) => setKeyword(e.target.value)} />
                </div>
                <button type="submit" className="bg-blue-600 text-white px-8 py-3 rounded-full font-bold hover:bg-blue-700 transition">
                    İş Bul
                </button>
            </form>

            <div className="space-y-4">
                {hasSearched && jobs.length === 0 && (
                    <div className="text-center text-gray-500 py-10 bg-white rounded-lg shadow">Aradığınız kriterlere uygun ilan bulunamadı.</div>
                )}
                
                {jobs.map(job => (
                    <div key={job.id} className="bg-white p-6 rounded-lg shadow-sm border border-gray-100 hover:shadow-md transition">
                        <div className="flex justify-between items-start">
                            <div>
                                <h2 className="text-2xl font-bold text-gray-800">{job.title}</h2>
                                <div className="flex items-center text-gray-500 mt-2 space-x-4">
                                    <span className="flex items-center"><MapPin size={16} className="mr-1"/> {job.location}</span>
                                    <span className="flex items-center"><Building size={16} className="mr-1"/> Şirket İlanı</span>
                                </div>
                                <p className="mt-4 text-gray-600 leading-relaxed">{job.description}</p>
                            </div>
                            <button onClick={() => handleApply(job.id)} className="bg-green-500 text-white px-6 py-2 rounded font-semibold hover:bg-green-600 transition">
                                Başvur
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
