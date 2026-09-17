import { createContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                const decoded = jwtDecode(token);
                // JWT içindeki verileri alıyoruz (email vb.)
                setUser({ email: decoded.sub, role: localStorage.getItem('role') });
            } catch (error) {
                localStorage.removeItem('token');
                localStorage.removeItem('role');
            }
        }
    }, []);

    const login = (token, role) => {
        localStorage.setItem('token', token);
        localStorage.setItem('role', role);
        const decoded = jwtDecode(token);
        setUser({ email: decoded.sub, role });
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        setUser(null);
        window.location.href = '/login';
    };

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
