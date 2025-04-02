import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Role {
    private int roleNumber;
    private String roleName;
    private String roleLevel;

    public Role(int roleNumber, String roleName, String roleLevel) {
        this.roleNumber = roleNumber;
        this.roleName = roleName;
        this.roleLevel = roleLevel;
    }

    public String getRoleName() {
        return roleName;
    }
}