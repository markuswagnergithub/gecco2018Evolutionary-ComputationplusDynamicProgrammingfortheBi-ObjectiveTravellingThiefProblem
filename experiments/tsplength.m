function [ output_args ] = tsplength( input_args )
%TSPLENGTH Summary of this function goes here
%   Detailed explanation goes here
    
%     disp(input_args);
    if startsWith(input_args, '#')
        input_args = extractAfter(input_args, "#");
    end
    
    if endsWith(input_args, ' reversed')
        input_args = extractBefore(input_args, ' reversed');
    end
    
    file = strcat(input_args , '.length');
%     disp(file);
    f = fopen(file, 'r');
    output_args = fscanf(f,'%d');
    fclose(f);
    
end

