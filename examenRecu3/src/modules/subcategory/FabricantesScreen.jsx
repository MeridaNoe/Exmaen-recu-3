import React, { useEffect, useState } from "react";
import { Badge, Card, Col, Row } from "react-bootstrap";
import DataTable from "react-data-table-component";
import { ButtonCircle } from "../../shared/components/ButtonCircle";
import { Loading } from "../../shared/components/Loading";
import AxiosClient from "../../shared/plugins/axios";
import { FilterComponent } from "../../shared/components/FilterComponent";
import { CategoryForm } from "./components/SubCategoryForm";
import { EditCategoryForm } from "./components/EditSubCategoryForm";
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

export const FabricantesScreen = () => {
  const [subCategories, setSubCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [filterText, setFilterText] = useState("");
  const [isOpen, setIsOpen] = useState(false);

  const filteredCategories = subCategories.filter(
    (fabricantes) =>
    fabricantes
  );

  const getCategories = async () => {
    try {
      try {
        setIsLoading(true);
        const data = await AxiosClient({ url: "/fabricante/searchNombre/" });
        console.log(data);
        if (!data.error) setSubCategories(data);
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

  const enableOrDisable = (row) => {
    Alert.fire({
      title: confirmTitle,
      text: confirmMsj,
      icon: "warning",
      confirmButtonColor: "#009574",
      confirmButtonText: "Aceptar",
      cancelButtonColor: "#DD6B55",
      cancelButtonText: "Cancelar",
      reverseButtons: true,
      backdrop: true,
      showCancelButton: true,
      showLoaderOnConfirm: true,
      allowOutsideClick: () => !Alert.isLoading,
      preConfirm: async () => {
        row.status = !row.status;
        try {
          const response = await AxiosClient({
            method: "POST",
            url: "/category/",
            data: JSON.stringify(row),
          });
          if (!response.error) {
            setSubCategories((subCategories) => [response.data, ...subCategories]);
            Alert.fire({
              title: successTitle,
              text: successMsj,
              icon: "success",
              confirmButtonColor: "3085d6",
              confirmButtonText: "Aceptar",
            })
          } else{
            row.status = !row.status;
          }
          return response;
        } catch (error) {
          Alert.fire({
            title: errorTitle,
            text: errorMsj,
            icon: "error",
            confirmButtonColor: "3085d6",
            confirmButtonText: "Aceptar",
          })
        } finally{
          getCategories();
        }
      },
    });
  };

  const columns = React.useMemo(() => [
    {
      name: "#",
      cell: (row) => <div>{row.id}</div>, // selector prpiedad que se mostrara en las filas
      sortable: true,
    },
    {
      name: "Nombre",
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
              setSubCategories={setSubCategories}
            />
            <EditCategoryForm
              isOpen={isEditing}
              onClose={() => setIsEditing(false)}
              setSubCategories={setSubCategories}
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
