import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axiosConfig';

export default function Register() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('CANDIDATE');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await api.post('/users/register', { email, password, role });
            alert('Kayıt başarılı! Lütfen giriş yapın.');
            navigate('/login');
        } catch (err) {
            setError('Kayıt başarısız! Lütfen bilgilerinizi kontrol edin.');
        }
    };

    return (
        <div className="flex items-center justify-center mt-20">
            <div className="bg-white p-8 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-6 text-center text-gray-800">Kayıt Ol</h2>
                {error && <div className="bg-red-100 text-red-600 p-3 rounded mb-4 text-sm">{error}</div>}
                
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Hesap Türü</label>
                        <select className="mt-1 w-full p-2 border border-gray-300 rounded focus:ring-blue-500 focus:border-blue-500"
                            value={role} onChange={(e) => setRole(e.target.value)}>
                            <option value="CANDIDATE">İş Arayan (Aday)</option>
                            <option value="EMPLOYER">Şirket (İşveren)</option>
                        </select>
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">E-posta</label>
                        <input type="email" required className="mt-1 w-full p-2 border border-gray-300 rounded focus:ring-blue-500 focus:border-blue-500"
                            value={email} onChange={(e) => setEmail(e.target.value)} />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700">Şifre</label>
                        <input type="password" required className="mt-1 w-full p-2 border border-gray-300 rounded focus:ring-blue-500 focus:border-blue-500"
                            value={password} onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <button type="submit" className="w-full bg-green-600 text-white p-2 rounded hover:bg-green-700 font-bold transition">
                        Kayıt Ol
                    </button>
                </form>
            </div>
        </div>
    );
}
