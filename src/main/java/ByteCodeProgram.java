public class ByteCodeProgram {
    private ByteCode[] program;
    private int num_elements;
    private int size;

    public ByteCodeProgram() {
        this.size = 10;
        this.program = new ByteCode[this.size];
        this.num_elements = 0;
    }

    public String toString() {
        String chain ="Programa almacenado:\n";
        for(int i = 0; i < this.num_elements; i++) {
            chain += i + ": " + this.program[i].getName() + " " + this.program[i].getParam() + "\n";
        }
        return chain;
    }

    public int getSize() {
        return this.size;
    }

    public void resize(int _numElements) {
        if(_numElements >= this.size) {
            ByteCode[] new_program = new ByteCode[this.size * 2];
            for (int i = 0; i < this.size; i++) {
                new_program[i] = this.program[i];
            }
            this.program = new_program;
        }
    }

    public void addByteCode(ByteCode _byteCode) {
        if(this.num_elements >= this.size) {
            this.resize(this.size);
            this.program[this.num_elements] = _byteCode;
            this.num_elements++;
        } else {
            this.program[this.num_elements] = _byteCode; //lanza excepcion
            num_elements++;
        }

    }

    public boolean replaceByteCode(ByteCode _byteCode, int _i) {
        this.program[_i] = _byteCode;
        return true;
    }

    public String runProgram(CPU _cpu) {
        String mensaje = "";
        for(int i = 0; i < this.num_elements; i++) {
            if(!_cpu.isHalt() && _cpu.execute(this.program[i])) {
                mensaje += "\n-----------------\nEl estado de la máquina tras ejecutar " + this.program[i].getName() + " " + this.program[i].getParam() + " es:\n" + _cpu.toString() + "\n";
            } else if (_cpu.isHalt()){
                mensaje += "HALT\n";
            }
        }
        _cpu.erase();
        _cpu.runCPU();
        return mensaje;
    }

    public void reset() {
        this.num_elements = 0;
        this.program = new ByteCode[this.size];
    }

}
