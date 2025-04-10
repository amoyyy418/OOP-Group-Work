package as;

class Phone {
    private int areaCode;
    private int exchange;
    private int lineNumber;

    public Phone(int areaCode, int exchange, int lineNumber) {
        this.areaCode = areaCode;
        this.exchange = exchange;
        this.lineNumber = lineNumber;
    }

    public String getFullNumber() {
        return String.format("%d-%d-%d", areaCode, exchange, lineNumber);
    }

    public static Phone fromString(String s) {
        String[] parts = s.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Phone number must be in format XXX-XXX-XXXX");
        }
        int areaCode = Integer.parseInt(parts[0]);
        int exchange = Integer.parseInt(parts[1]);
        int lineNumber = Integer.parseInt(parts[2]);
        return new Phone(areaCode, exchange, lineNumber);
    }
    
    @Override
    public String toString() {
        return getFullNumber();
    }
}

