import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import EmployerDashboard from './pages/EmployerDashboard';

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="min-h-screen">
          <Navbar />
          <main className="px-4 sm:px-6 lg:px-8 pb-12">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/employer" element={<EmployerDashboard />} />
            </Routes>
          </main>
        </div>
      </Router>
    </AuthProvider>
  );
}

export default App;
