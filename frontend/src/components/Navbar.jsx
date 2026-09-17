import { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { Briefcase, LogOut, User as UserIcon } from 'lucide-react';

export default function Navbar() {
    const { user, logout } = useContext(AuthContext);

    return (
        <nav className="bg-blue-600 text-white shadow-lg">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16 items-center">
                    <Link to="/" className="flex items-center space-x-2 font-bold text-xl">
                        <Briefcase size={24} />
                        <span>Kariyer.net Klon</span>
                    </Link>
                    
                    <div className="flex space-x-4 items-center">
                        {!user ? (
                            <>
                                <Link to="/login" className="hover:text-blue-200">Giriş Yap</Link>
                                <Link to="/register" className="bg-white text-blue-600 px-4 py-2 rounded-md font-medium hover:bg-gray-100">Kayıt Ol</Link>
                            </>
                        ) : (
                            <>
                                {user.role === 'EMPLOYER' && (
                                    <Link to="/employer" className="hover:text-blue-200">İşveren Paneli</Link>
                                )}
                                <div className="flex items-center space-x-2 bg-blue-700 px-3 py-1 rounded-full">
                                    <UserIcon size={18} />
                                    <span className="text-sm">{user.email}</span>
                                </div>
                                <button onClick={logout} className="flex items-center space-x-1 hover:text-red-300">
                                    <LogOut size={18} />
                                    <span>Çıkış</span>
                                </button>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}
