import React, { useEffect, useState } from "react";
import { Badge, Card, Col, Row } from "react-bootstrap";
import DataTable from "react-data-table-component";
import { ButtonCircle } from "../../shared/components/ButtonCircle";
import { Loading } from "../../shared/components/Loading";
import AxiosClient from "../../shared/plugins/axios";
import { FilterComponent } from "../../shared/components/FilterComponent";
import { CategoryForm } from "./components/CategoryForm";
import { EditCategoryForm } from "./components/EditCategoryForm";
import Alert, {
  confirmMsj,
  confirmTitle,
  errorMsj,
  errorTitle,
  successMsj,
  successTitle,
} from "../../shared/plugins/alerts";

const options = {
  rowsPerPageText: "Regsitros por pagina",
  rangeSeparatorText: "de",
};

export const CategoryScreen = () => {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [filterText, setFilterText] = useState("");
  const [isOpen, setIsOpen] = useState(false);

  const filteredCategories = categories.filter(
    (fabricante) =>
    fabricante
  );

  const getCategories = async () => {
    try {
      try {
        setIsLoading(true);
        const data = await AxiosClient({ url: "/fabricante/searchById/" });
        console.log(data)
        if (!data.error) setCategories(data);
      } catch (error) {
      } finally {
        setIsLoading(false);
      }
    } catch (error) {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    getCategories();
  }, []);

  const headerComponent = React.useMemo(() => {
    const handlerClear = () => {
      if (filterText) setFilterText("");
    };
    return (
      <FilterComponent
        onFilter={(e) => setFilterText(e.target.value)}
        onClear={handlerClear}
        filterText={filterText}
      />
    );
  }, [filterText]); // memoriza lo que nosotros le pasemos en la funcion y lo renderiza.

 

  const columns = React.useMemo(() => [
    {
      name: "#",
      cell: (row, index) => <div>{index + 1}</div>, // selector prpiedad que se mostrara en las filas
      sortable: true,
    },
    {
      name: "Nombre del Fabricante",
      cell: (row) => <div>{row.nombre}</div>,
      sortable: true,
      selector: (row) => row.nombre,
    }
  ]);

  return (
    <Card>
      <Card.Header>
        <Row>
          <Col>Catgeorias</Col>
          <Col className="text-end">
            <ButtonCircle
              type={"btn btn-outline-success"}
              onClick={() => setIsOpen(true)}
              icon="plus"
              size={16}
            />
            <CategoryForm
              isOpen={isOpen}
              onClose={() => setIsOpen(false)}
              setCategories={setCategories}
            />
            <EditCategoryForm
              isOpen={isEditing}
              onClose={() => setIsEditing(false)}
              setCategories={setCategories}
              category={selectedCategory}
            />
          </Col>
        </Row>
      </Card.Header>
      <Card.Body>
        <DataTable
          columns={columns}
          data={filteredCategories}
          progressPending={isLoading}
          progressComponent={<Loading />}
          noDataComponent={"Sin registros"}
          pagination
          paginationComponentOptions={options}
          subHeader
          subHeaderComponent={headerComponent}
          persistTableHead
          striped={true}
          highlightOnHover={true}></DataTable>
      </Card.Body>
    </Card>
  );
};
