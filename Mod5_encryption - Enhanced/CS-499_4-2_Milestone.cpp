// Encryption.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <cassert>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <sstream>
#include <ctime>
#include <stdexcept>   // ENHANCEMENT: for std::invalid_argument in key validation
#include <string>


std::string encrypt_decrypt(const std::string& source, const std::string& key)
{
    // ENHANCEMENT: Check for empty key and throw a clear exception instead of relying
    // only on assert. This makes the algorithm safer in both Debug and Release builds.
    if (key.empty()) {
        throw std::invalid_argument("Encryption key must not be empty.");
    }

    const auto key_length = key.length();
    const auto source_length = source.length();

    // keep the original asserts as a second line of defense
    assert(key_length > 0);
    assert(source_length > 0);

    // ENHANCEMENT: reserve capacity and use push_back for slightly clearer logic
    std::string output;
    output.reserve(source_length);

    // loop through the source string char by char
    for (size_t i = 0; i < source_length; ++i)
    {
        // transform each character based on an XOR of the key, repeated using modulo
        char transformed = static_cast<char>(source[i] ^ key[i % key_length]);
        output.push_back(transformed);
    }

    // our output length must equal our source length
    assert(output.length() == source_length);

    // return the transformed string
    return output;
}

// Read file into a single string
std::string read_file(const std::string& filename)
{
    std::ifstream file(filename);

    if (!file.is_open()) {
        // ENHANCEMENT: More detailed error output so it is easier to diagnose
        // path problems when the input file is not found.
        std::cerr << "Error: Could not open file \"" << filename << "\" for reading.\n";
        return "";
    }

    std::ostringstream ss;
    ss << file.rdbuf();        // Read entire file into stream
    return ss.str();           // Return file content as string
}

// Extract the student name from the first line of the input text
std::string get_student_name(const std::string& string_data)
{
    std::string student_name;

    // find the first newline
    size_t pos = string_data.find('\n');
    // did we find a newline
    if (pos != std::string::npos)
    {
        // copy that substring as the student name
        student_name = string_data.substr(0, pos);
    }

    return student_name;
}

// Save the processed data to a file with metadata
void save_data_file(const std::string& filename,
    const std::string& student_name,
    const std::string& key,
    const std::string& data)
{
    //  file format
    //  Line 1: student name
    //  Line 2: timestamp (yyyy-mm-dd)
    //  Line 3: key used
    //  Line 4+: data

    std::ofstream file(filename);
    if (file.is_open())
    {
        // Line 1: student name
        file << student_name << std::endl;

        // Line 2: timestamp (yyyy-mm-dd)
        time_t now = time(nullptr);
        tm local_time{};
        localtime_s(&local_time, &now);
        file << std::put_time(&local_time, "%Y-%m-%d") << std::endl;

        // Line 3: key used
        file << key << std::endl;

        // Line 4+: encrypted/decrypted data
        file << data << std::endl;

        file.close();
    }
    else
    {
        std::cerr << "Error: Could not open \"" << filename << "\" for writing." << std::endl;
    }
}

int main()
{
    std::cout << "Encryption/Decryption Test!" << std::endl;

    // input file format
    // Line 1: <students name>
    // Line 2: <Lorem Ipsum Generator website used>
    // Lines 3+: <lorem ipsum generated with 3 paragraphs>

    const std::string file_name = "inputdatafile.txt";
    const std::string encrypted_file_name = "encrypteddatafile.txt";
    const std::string decrypted_file_name = "decrypteddatafile.txt";
    const std::string key = "password";

    // read the source file
    std::string source_string = read_file(file_name);

    if (source_string.empty()) {
        // ENHANCEMENT: Give message and exit early if the file could not be read,
        // instead of continuing with an empty string.
        std::cerr << "No input data was read from \"" << file_name
            << "\". Make sure the file is in the program's working directory."
            << std::endl;
        std::cout << "Press any key to exit." << std::endl;
        std::cin.get();
        return 1;
    }

    // get the student name from the data file
    const std::string student_name = get_student_name(source_string);

    try {
        // encrypt sourceString with key
        const std::string encrypted_string = encrypt_decrypt(source_string, key);

        // save encrypted_string to file
        save_data_file(encrypted_file_name, student_name, key, encrypted_string);

        // decrypt encryptedString with key
        const std::string decrypted_string = encrypt_decrypt(encrypted_string, key);

        // save decrypted_string to file
        save_data_file(decrypted_file_name, student_name, key, decrypted_string);
    }
    catch (const std::exception& ex) {
        // ENHANCEMENT: catch any exceptions from encryption and report them cleanly
        std::cerr << "Encryption error: " << ex.what() << std::endl;
        std::cout << "Press any key to exit." << std::endl;
        std::cin.get();
        return 1;
    }

    std::cout << "Read File: " << file_name
        << " - Encrypted To: " << encrypted_file_name
        << " - Decrypted To: " << decrypted_file_name << std::endl;

    std::cout << "Program complete. Press any key to exit." << std::endl;
    std::cin.get();
    return 0;
}
