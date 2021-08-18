class Index extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            status: []
        };
    }
    
    componentDidMount() {
    }
  
    render() {
        const {error, isLoaded, status} = this.state;

            return (
                    <div> 
                        <h1>holi:</h1>

                    </div>
               
                    );
        
    }
}

ReactDOM.render(
        <Index />,
        document.getElementById('reactFront')
);

