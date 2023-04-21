import { useContext } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import { AuthContext } from '../../modules/auth/authContext';
import { LoginScreen } from '../../modules/auth/LoginScreen';
import { PublicNavbar } from '../components/PublicNavbar'
import AdminNavbar from './AdminNavbar';
import { CategoryScreen } from '../../modules/category/CategoryScreen';
import { SubCategoryScreen } from '../../modules/subcategory/SubCategoryScreen';
import { ProductosScreen } from '../../modules/subcategory/ProductosScreen';
import { FabricantesScreen } from '../../modules/subcategory/FabricantesScreen';

export const AppRouter = () => {
  const { user } = useContext(AuthContext);
  return (
    <Router>
      <Routes>
        <Route path="/auth" element={<LoginScreen />} />
        <Route
          path="/*"
          element={
              <>
                <AdminNavbar/>
                <Container style={{ marginTop: '20px' }}>
                  <Routes>
                    <Route path="products" element={<>PRODUCTS</>} />
                    <Route path="category" element={<CategoryScreen/>} />
                    <Route path="subcategory" element={<SubCategoryScreen/>} />
                    <Route path="masproductos" element={<ProductosScreen/>} />
                    <Route path="masfabricantes" element={<FabricantesScreen/>} />
                    <Route index element={<>INDEX</>} />
                    <Route path="*" element={<>404</>} />
                  </Routes>
                </Container>
              </>
            
          }
        />
        <Route path="*" element={<>404</>} />
      </Routes>
    </Router>
  );
};
