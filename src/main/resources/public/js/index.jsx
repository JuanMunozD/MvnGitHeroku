const {
  colors,
  CssBaseline,
  ThemeProvider,
  Typography,
  Container,
  makeStyles,
  createTheme,
  Box,
  SvgIcon,
  Link,
  Backdrop,
  Fade,
  Modal,
  TextField,
  DataGrid,
  TableContainer,
  Paper,
  TableHead,
  Table,
  TableBody,
  TableCell,
  TableRow,
  TablePagination,
  Icon,
  Button,
  CircularProgress,
} = MaterialUI;

// Create a theme instance.
const theme = createTheme({
  palette: {
    primary: {
      main: '#556cd6',
    },
    secondary: {
      main: '#19857b',
    },
    error: {
      main: colors.red.A400,
    },
    background: {
      default: '#fff',
    },
  },
});

const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  table: {
    minWidth: 650,
  },
  root: {
    width: '100%',
  },
  container: {
    maxHeight: 300,
  },
  button: {
    margin: theme.spacing(1),
  },
  backdrop: {
    zIndex: theme.zIndex.drawer + 1,
    color: '#fff',
  },
}));

function ActionTable(props) {
  const classes = useStyles();
  const { dataAction } = props;

  const [rows, setRows] = React.useState([]);

  function createData(date, open, high, low, close, volume) {

    return { date, open, high, low, close, volume };
  }


  const formDataTable = (data) => {
    var temp = Object.values(data)

    var tempValues = Object.values(temp[1])
    var tempKeys = Object.keys(temp[1])

    var arrayTemp = []

    for (var i = 0; i < tempValues.length; i++) {
      var valTemp = Object.values(tempValues[i])
      arrayTemp.push(createData(tempKeys[i], valTemp[0], valTemp[1], valTemp[2], valTemp[3], valTemp[4]))
    }
    setRows(arrayTemp)
  }


  React.useEffect(async () => {
    // Actualiza la datade la tabla
    formDataTable(dataAction)

  }, [dataAction]);

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const columns = [
    { id: 'date', label: 'Date', minWidth: 170 },
    { id: 'open', label: 'Open', minWidth: 100 },
    {
      id: 'high',
      label: 'High',
      minWidth: 170,
      align: 'right',
    },
    {
      id: 'low',
      label: 'Low',
      minWidth: 170,
      align: 'right',
    },
    {
      id: 'close',
      label: 'Close',
      minWidth: 170,
      align: 'right',
    },
    {
      id: 'volume',
      label: 'Volume',
      minWidth: 170,
      align: 'right',
    },
  ];


  return (
    <Paper className={classes.root}>
      <TableContainer className={classes.container}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((row, index) => {
              return (
                <TableRow hover role="checkbox" tabIndex={-1} key={index}>
                  {columns.map((column) => {
                    const value = row[column.id];
                    return (
                      <TableCell key={column.id} align={column.align}>
                        {column.format && typeof value === 'number' ? column.format(value) : value}
                      </TableCell>
                    );
                  })}
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}


function TransitionsModal(props) {
  const classes = useStyles();
  const { title, userAction } = props;
  const [open, setOpen] = React.useState(false);
  const [openLoad, setOpenLoad] = React.useState(false);

  const [data, setData] = React.useState(null);
  const [informacion, setInformacion] = React.useState("");

  const llamadoAxios = async (x) => {
    if(userAction.length != 0){
      await axios.get('/action/' + x + "/" + userAction)
      .then(response => {
        setOpenLoad(false)
        if (response.data.data["Error Message"]) {
          Swal.fire({
            title: 'Error Codigo!',
            text: 'No hay accion asociada al codigo ingresado: ' + userAction,
            icon: 'error',
            confirmButtonText: 'cerrar'
          })
        } else if (response.data.data["Note"]) {
          Swal.fire({
            title: 'Error Codigo!',
            text: response.data.data["Note"],
            icon: 'error',
            confirmButtonText: 'cerrar'
          })
        }
        else {
          setData(response.data.data);
          setInformacion(response.data.data["Meta Data"]["1. Information"])
          setOpenLoad(false)
          setOpen(true);
        }
      }).catch(e => {
        setOpen(false);
        setOpenLoad(false)
        Swal.fire({
          title: 'error 500!',
          text: 'error interno de servidor',
          icon: 'warning',
          confirmButtonText: 'cerrar'
        })
      })
    }else{
      setOpen(false);
      setOpenLoad(false)
      Swal.fire({
        title: 'Error campo vacio!',
        text: 'Ingrese el codigo de la accion a buscar',
        icon: 'error',
        confirmButtonText: 'cerrar'
      })

    }

  }


  const getApi = (metodo) => {

    if (metodo === 'Intra dia') {
      llamadoAxios('interday');
    } else if (metodo === 'Dia') {
      llamadoAxios('day');
    } else if (metodo === 'Semana') {
      llamadoAxios('week');
    } else if (metodo === 'Mes') {
      llamadoAxios('month');
    }

  };

  const handleOpen = () => {
    setOpenLoad(true)
    getApi(title)
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Backdrop className={classes.backdrop} open={openLoad}>
        <CircularProgress color="inherit" />
      </Backdrop>
      <Button
        size="small"
        variant="contained"
        color="primary"
        className={classes.button}
        endIcon={<Icon>send</Icon>}
        onClick={handleOpen}
      >
        {title}
      </Button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <Container fixed>
            <div className={classes.paper}>
              <center>
                <h2 id="transition-modal-title">Information: {informacion}</h2>
                <h2 id="transition-modal-title">Symbol: {userAction}</h2>
              </center>
              {data &&
                <ActionTable dataAction={data} />}
              <p />
              <center>
                <Button
                  variant="contained"
                  color="primary"
                  onClick={handleClose}
                >
                  Cerrar
                </Button>
              </center>
            </div>
          </Container>
        </Fade>
      </Modal>
    </div>
  );
}


function App() {

  const [actionUser, setActionUser] = React.useState("");

  const handlemodel = (e) => {
    setActionUser(e.target.value.toUpperCase())
  }

  return (
    <Container fixed>
      <Paper elevation={3}>
      <center>
        <div style={{ marginTop: 24, alignItems: 'center', justifyContent: 'center' }}>
          <Typography variant="h4" component="h1" gutterBottom>
            Aplicación para la Consulta de Acciones en el Mercado
          </Typography>

          <TextField required name='actionUser' id="filled-basic" label="Accion" variant="filled" onChange={handlemodel} value={actionUser} />

          <TransitionsModal userAction={actionUser} title={"Intra dia"} />
          <TransitionsModal userAction={actionUser} title={"Dia"} />
          <TransitionsModal userAction={actionUser} title={"Semana"} />
          <TransitionsModal userAction={actionUser} title={"Mes"} />
        </div>
      </center>
      </Paper>
    </Container>
  );
}

ReactDOM.render(
  <ThemeProvider theme={theme}>
    {/* CssBaseline kickstart an elegant, consistent, and simple baseline to build upon. */}
    <CssBaseline />
    <App />
  </ThemeProvider>,
  document.querySelector('#root'),
);